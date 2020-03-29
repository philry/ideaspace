package com.itany.nmms.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.itany.mvc.annotation.RequestMapping;
import com.itany.mvc.annotation.ResponseBody;
import com.itany.mvc.util.CommonsMultipartFile;
import com.itany.nmms.constant.DictConstant;
import com.itany.nmms.constant.ResponseCodeConstant;
import com.itany.nmms.entity.Product;
import com.itany.nmms.entity.ProductType;
import com.itany.nmms.exception.FileUploadException;
import com.itany.nmms.exception.ParameterException;
import com.itany.nmms.factory.ObjectFactory;
import com.itany.nmms.service.ProductService;
import com.itany.nmms.service.ProductTypeService;
import com.itany.nmms.util.ParameterUtil;
import com.itany.nmms.util.ResponseResult;

@RequestMapping("/product")
public class ProductAction {

	private ProductTypeService typeService = (ProductTypeService) ObjectFactory.getObject("typeService");
	private ProductService productService = (ProductService) ObjectFactory.getObject("productService");
	@RequestMapping("/findAll")
	public String findAll(HttpServletRequest request,
			HttpServletResponse response) {
		
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");

		if (ParameterUtil.isNull(pageNo)) {
			pageNo = DictConstant.PAGE_NO_DEFAULT;
		}
		if (ParameterUtil.isNull(pageSize)) {
			pageSize = DictConstant.PAGE_SIZE_DEFAULT;
		}

		//开始使用分页插件
		//1.设置分页属性,当前页与一页显示多少条
		PageHelper.startPage(Integer.parseInt(pageNo),
				Integer.parseInt(pageSize));

		//2.查询业务处理的数据
		List<Product> products = productService.findAll();
		
		//3.对业务数据进行加工，封装成分页对象
		PageInfo<Product> pageProducts = new PageInfo<Product>(products);
		
		
		List<ProductType> types = typeService.findEnable();
		request.setAttribute("types", types);
		request.setAttribute("pageProducts", pageProducts);
		
		return "backend/productManager";
	}
	
	@RequestMapping("/addProduct")
	public String addProduct(HttpServletRequest request,
			HttpServletResponse response,CommonsMultipartFile file) {

		String name = request.getParameter("name");
		String price = request.getParameter("price");
		String productTypeId = request.getParameter("productTypeId");
		Product product = new Product();
		product.setName(name);
		product.setPrice(Double.parseDouble(price));
		ProductType productType = new ProductType();
		productType.setId(Integer.parseInt(productTypeId));
		product.setProductType(productType);
		
		
		try {
			productService.addProduct(product, file, request.getSession());
		} catch (FileUploadException e) {
			request.setAttribute("productMsg", e.getMessage());
		}
		return "redirect:/product/findAll.do";
		
	}
	
	@ResponseBody
	@RequestMapping("/findById")
	public ResponseResult method(HttpServletRequest request,
			HttpServletResponse response) {
		ResponseResult result = new ResponseResult();

		String id = request.getParameter("id");
		
		try {
			Product product = productService.findById(id);
			result.setMessage("成功");
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_SUCCESS);
			result.setReturnObject(product);
		} catch (ParameterException e) {
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_REQUEST_PARAMTER_ERROR);
			result.setMessage(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			result.setResponseCode(ResponseCodeConstant.RESPONSE_CODE_FAIL);
			result.setMessage("服务器内部异常");
		}
		
		return result;
	}
	
	@RequestMapping("/modifyProduct")
	public String modifyProduct(HttpServletRequest request,
			HttpServletResponse response,CommonsMultipartFile file) {

		String id = request.getParameter("modifyId");
		String name = request.getParameter("modifyName");
		String price = request.getParameter("modifyPrice");
		String productTypeId = request.getParameter("modifyTypeId");
		Product product = new Product();
		product.setProductId(Integer.parseInt(id));
		product.setName(name);
		product.setPrice(Double.parseDouble(price));
		ProductType productType = new ProductType();
		productType.setId(Integer.parseInt(productTypeId));
		product.setProductType(productType);
		
		
		try {
			productService.modifyProduct(product, file, request.getSession());
		} catch (FileUploadException e) {
			request.setAttribute("productMsg", e.getMessage());
		}
		return "redirect:/product/findAll.do";
		
	}
}
