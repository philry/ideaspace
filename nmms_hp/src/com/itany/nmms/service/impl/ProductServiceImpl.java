package com.itany.nmms.service.impl;

import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.nmms.constant.DictConstant;
import com.itany.nmms.dao.ProductMapper;
import com.itany.nmms.dao.SequenceMapper;
import com.itany.nmms.entity.Product;
import com.itany.nmms.entity.Sequence;
import com.itany.nmms.factory.ObjectFactory;
import com.itany.nmms.service.ProductService;
import org.apache.commons.fileupload.FileUploadException;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ProductServiceImpl implements ProductService {


    @Override
    public void insert(String name, String price, CommonsMultipartFile file, String id, HttpSession session) throws FileUploadException {
        ProductMapper productMapper= (ProductMapper) ObjectFactory.getObject("productMapper");
        SequenceMapper sequenceMapper= (SequenceMapper) ObjectFactory.getObject("sequenceMapper");
        //首先需要生成商品编号
        //编号规则:前缀+日期+序列号
        Product product=new Product();
        Sequence sequence=sequenceMapper.SelectByName(DictConstant.PRODUCT_NO_PREFIX);
        if(sequence==null){
            Sequence seq = new Sequence();
            seq.setName(DictConstant.PRODUCT_NO_PREFIX);
            seq.setValue(DictConstant.PRODUCT_NO_SEQUENCE_INIT);
            sequenceMapper.insert(seq);
            product.setNo(DictConstant.PRODUCT_NO_PREFIX+new SimpleDateFormat("yyyyMMdd").format(new Date())+seq.getValue());
        }else{
            if(DictConstant.PRODUCT_NO_SEQUENCE_MAX.equals(sequence.getValue())){
                sequence.setValue(DictConstant.PRODUCT_NO_SEQUENCE_INIT);
            }else{
                sequence.setValue(String.format("%06d",Integer.parseInt(sequence.getValue())+1));
            }
            sequenceMapper.update(sequence);
            product.setNo(DictConstant.PRODUCT_NO_PREFIX+new SimpleDateFormat("yyyyMMdd").format(new Date())+sequence.getValue());
        }
        product.setId(Integer.parseInt(id));
        product.setName(name);
        product.setPrice(Double.parseDouble(price));
        String path=DictConstant.PRODUCT_IMAGE_ROOT_FOLDER_NAME+new SimpleDateFormat("yyyyMMdd").format(new Date());
        String cp=session.getServletContext().getRealPath(path);

        File f=new File(path);
        f.mkdirs();
        product.setImage(path+"/"+file.getOriginalFilename());
        productMapper.insertProduct(product);

        try {
            file.transferTo(new File(cp,file.getOriginalFilename()));
        } catch (Exception e) {
            e.printStackTrace();
            throw new FileUploadException("文件上传出错");
        }
    }

    @Override
    public List<Product> findAll() {
        ProductMapper productMapper= (ProductMapper) ObjectFactory.getObject("productMapper");
        List<Product> products=productMapper.findAll();
        return  products;
    }
}
