package com.itany.nmms.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.nmms.constant.DictConstant;
import com.itany.nmms.dao.ProductDao;
import com.itany.nmms.dao.SequenceDao;
import com.itany.nmms.entity.Product;
import com.itany.nmms.entity.Sequence;
import com.itany.nmms.exception.FileUploadException;
import com.itany.nmms.exception.ParameterException;
import com.itany.nmms.factory.ObjectFactory;
import com.itany.nmms.service.ProductService;
import com.itany.nmms.util.ParameterUtil;

public class ProductServiceImpl implements ProductService{

	public void addProduct(Product product, CommonsMultipartFile file,
			HttpSession session) throws FileUploadException {
		SequenceDao sequenceDao = (SequenceDao) ObjectFactory.getObject("sequenceDao");
		ProductDao productDao = (ProductDao) ObjectFactory.getObject("productDao");
		//生成对应的商品编号
		//编号规则:前缀+日期+序列号
		//首先生成对应的序列号
		//根据前缀查询序列号表,判断当前前缀是否存在
		Sequence selectSequence = sequenceDao.selectByName(DictConstant.PRODUCT_NO_PREFIX);
		//如果当前的sequence为空,表示尚未开始使用商品编号
		if(selectSequence == null){
			//则此时使用初始化的序列号值为当前商品新增一个商品编号
			//且将当前的序列号值存储到数据库
			Sequence sequence = new Sequence();
			sequence.setName(DictConstant.PRODUCT_NO_PREFIX);
			sequence.setValue(DictConstant.PRODUCT_NO_INIT_VALUE);
			sequenceDao.insertSequence(sequence);
			
			//将商品编号交给product
			product.setProductNo(DictConstant.PRODUCT_NO_PREFIX+new SimpleDateFormat("yyyyMMdd").format(new Date())+DictConstant.PRODUCT_NO_INIT_VALUE);
		}else{
			String value = selectSequence.getValue();
			//若查询到对应的序列号信息
			//则根据当前的值判断下一个值
			//如果值为999999,则下一个值为000001
			//否则下一个值为当前值+1
			if(DictConstant.PRODUCT_NO_MAX_VALUE.equals(value)){
				value = DictConstant.PRODUCT_NO_INIT_VALUE;
			}else{
				value = String.format("%06d", Integer.parseInt(value)+1);
			}
			sequenceDao.updateValueByName(selectSequence.getName(), value);
			
			//将商品编号交给product
			product.setProductNo(DictConstant.PRODUCT_NO_PREFIX+new SimpleDateFormat("yyyyMMdd").format(new Date())+value);
		}
		
		
		
		//将image交给product
		String path = DictConstant.PRODUCT_IMAGE_FOLDER_NAME+new SimpleDateFormat("yyyyMMdd").format(new Date());
		String cp = session.getServletContext().getRealPath(path);
		
		File f = new File(cp);
		f.mkdirs();
		
		product.setImage(path+"/"+file.getOriginalFilename());
		productDao.insert(product);
		
		//上传商品图片
		try {
			file.transferTo(new File(cp,file.getOriginalFilename()));
		} catch (Exception e) {
			throw new FileUploadException("文件上传出错",e);
		}
		
		
	}


	public List<Product> findAll() {
		ProductDao productDao = (ProductDao) ObjectFactory.getObject("productDao");
		return productDao.selectAll();
	}


	public Product findById(String id) throws ParameterException {
		ProductDao productDao = (ProductDao) ObjectFactory.getObject("productDao");
		if(ParameterUtil.isNull(id)){
			throw new ParameterException("id不能为空");
		}
		return productDao.selectById(Integer.parseInt(id));
	}


	public void modifyProduct(Product product, CommonsMultipartFile file,
			HttpSession session) throws FileUploadException {
		ProductDao productDao = (ProductDao) ObjectFactory.getObject("productDao");
		//将image交给product
		String path = DictConstant.PRODUCT_IMAGE_FOLDER_NAME+new SimpleDateFormat("yyyyMMdd").format(new Date());
		String cp = session.getServletContext().getRealPath(path);
		
		File f = new File(cp);
		f.mkdirs();
		
		product.setImage(path+"/"+file.getOriginalFilename());
		productDao.updateProduct(product);
		
		//上传商品图片
		try {
			file.transferTo(new File(cp,file.getOriginalFilename()));
		} catch (Exception e) {
			throw new FileUploadException("文件上传出错",e);
		}
	}

}
