package com.itany.nmms.service.impl;

import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.nmms.constant.DictConstant;
import com.itany.nmms.dao.ProductMapper;
import com.itany.nmms.dao.SequenceMapper;
import com.itany.nmms.entity.Product;
import com.itany.nmms.entity.Sequence;
import com.itany.nmms.entity.SequenceExample;
import com.itany.nmms.exception.FileUploadException;
import com.itany.nmms.factory.ObjectFactory;
import com.itany.nmms.service.ProductService;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Author:shixiaojun@itany.com
 * Date:2018/11/2 9:32
 * Description:
 * version:1.0
 */
public class ProductServiceImpl implements ProductService {
    @Override
    public void addProduct(String name, String price, CommonsMultipartFile file, String type, HttpSession session) throws FileUploadException {
        SequenceMapper sequenceMapper = (SequenceMapper) ObjectFactory.getObject("sequenceMapper");
        ProductMapper productMapper = (ProductMapper) ObjectFactory.getObject("productMapper");

        //保存商品，首先需要有对应的商品信息
        //生成对应的商品信息
        //首先需要生成商品编号
        //编号规则:前缀+日期+序列号
        Product product = new Product();

        //查询序列号表
        SequenceExample example = new SequenceExample();
        example.or()
                .andNameEqualTo(DictConstant.PRODUCT_NO_PREFIX);
        List<Sequence> sequences = sequenceMapper.selectByExample(example);
        //若不存在
        if(sequences.isEmpty()){
            //则此时表示当前的商品是第一个商品
            //尚未使用任何的序列号
            //因此向序列号表中插入一条新的数据
            //name值为前缀，value值为初始值
            Sequence sequence = new Sequence();
            sequence.setName(DictConstant.PRODUCT_NO_PREFIX);
            sequence.setValue(DictConstant.PRODUCT_NO_SEQUENCE_INIT);
            sequenceMapper.insertSelective(sequence);

            //生成对应的商品编号
            product.setProductNo(DictConstant.PRODUCT_NO_PREFIX+new SimpleDateFormat("yyyyMMdd").format(new Date())+sequence.getValue());
        }else{
            //若存在
            Sequence sequence = sequences.get(0);
            //判断当前的序列号的值是否达到了最大值
            //若达到最大值，则下一个值为初始值
            if(DictConstant.PRODUCT_NO_SEQUENCE_MAX.equals(sequence.getValue())){
                sequence.setValue(DictConstant.PRODUCT_NO_SEQUENCE_INIT);
            }else{
                //若没有达到最大值，则在原有的值得基础上加1
                //"%06d" 代表在数字前面加0,使数字为6位数字
                sequence.setValue(String.format("%06d",Integer.parseInt(sequence.getValue())+1));

            }
            //修改序列号的信息
            sequenceMapper.updateByPrimaryKeySelective(sequence);

            //生成对应的商品编号
            product.setProductNo(DictConstant.PRODUCT_NO_PREFIX+new SimpleDateFormat("yyyyMMdd").format(new Date())+sequence.getValue());
        }


        product.setName(name);
        product.setPrice(Double.parseDouble(price));

        //创建目录，上传文件，并将文件信息交给product，cp是服务器的绝对路径
        String path = DictConstant.PRODUCT_IMAGE_ROOT_FOLDER_NAME+new SimpleDateFormat("yyyyMMdd").format(new Date());
        String cp = session.getServletContext().getRealPath(path);


        //创建该目录
        //要求，如果有，不创建，如果没有，创建
        File f = new File(cp);
        f.mkdirs();

        product.setImage(path+"/"+file.getOriginalFilename());//file.getOriginalFilename()是获取文件名
        product.setProductTypeId(Integer.parseInt(type));

        productMapper.insertSelective(product);

        //上传文件
        try {
            file.transferTo(new File(cp,file.getOriginalFilename()));//file.getOriginalFilename()是获取文件名
        } catch (Exception e) {
            throw new FileUploadException("文件上传出错");
        }


    }
}
