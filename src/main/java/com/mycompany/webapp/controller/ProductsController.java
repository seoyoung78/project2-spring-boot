package com.mycompany.webapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mycompany.webapp.dto.Pager;
import com.mycompany.webapp.dto.Products;
import com.mycompany.webapp.dto.ProductsImg;
import com.mycompany.webapp.service.ProductsService;

@RestController
@RequestMapping("/products")
public class ProductsController {
	private static final Logger logger = LoggerFactory.getLogger(ProductsController.class);
	@Autowired
	private ProductsService productsService;

	@GetMapping("")
	public Map<String, Object> list(@RequestParam(defaultValue="1") int pageNo,
			@RequestParam(defaultValue="") String categoryVal,
			@RequestParam(defaultValue="") String keyword) {

		int totalRows;
		int categoryNo;
		Map<String, Object> map = new HashMap<>();

		if(keyword.equals("")) {	//키워드가 들어오지 않으면 실행
			if(categoryVal.equals("전체")) {	//카테고리가 전체이면 AllList
				totalRows = productsService.getCount();
				Pager pager = new Pager(10, 5, totalRows, pageNo);
				List<Products> list = productsService.getProductsAllList(pager);
				map.put("products", list);
				map.put("pager", pager);
			}else {	//그렇지 않으면 카테고리에 맞는 List
				if(categoryVal.equals("캔들")) {
					categoryNo = 1;
				}else if(categoryVal.equals("조명")) {
					categoryNo = 2;
				}else if(categoryVal.equals("트리")) {
					categoryNo = 3;
				}else {
					categoryNo = 4;
				}

				totalRows = productsService.getTotalCount(categoryNo);
				Pager pager = new Pager(10, 5, totalRows, pageNo);
				List<Products> list = productsService.getProductsList(pager, categoryNo);
				map.put("products", list);
				map.put("pager", pager);
			}
		}else {	// keyword가 들어왔으면
			if(categoryVal.equals("전체")) {
				totalRows = productsService.getTotalRows(keyword);
				Pager pager = new Pager(10, 5, totalRows, pageNo);
				List<Products> list = productsService.getProductsSearchListPager(pager, keyword);
				map.put("products", list);
				map.put("pager", pager);
			}else {
				if(categoryVal.equals("캔들")) {
					categoryNo = 1;
				}else if(categoryVal.equals("조명")) {
					categoryNo = 2;
				}else if(categoryVal.equals("트리")) {
					categoryNo = 3;
				}else {
					categoryNo = 4;
				}

				totalRows = productsService.getTotalCategoryKeywordCount(categoryNo, keyword);
		
				Pager pager = new Pager(10, 5, totalRows, pageNo);
				List<Products> list = productsService.getProductCategoryKeyword(pager, categoryNo, keyword);
				map.put("products", list);
				map.put("pager", pager);
			}
		}

		return map;
	}   

	@PostMapping("")
	public Products create(Products product) {
		productsService.insert(product);

		if(product.getBattach() != null && !(product.getBattach().length == 0)) {
			MultipartFile[] mf = product.getBattach();
			for(int i=0; i<mf.length; i++) {
				product.setImgOname(mf[i].getOriginalFilename());
				product.setImgSname(new Date().getTime() + "-" + mf[i].getOriginalFilename());
				product.setImgType("jpg");
				product.setImgState(i+1);
				try {
					File file;
					if(product.getProductCategoryNo() == 1) {
						file = new File("D:/상품사진들/캔들/" + product.getImgSname());
					}else if(product.getProductCategoryNo() == 2) {
						file = new File("D:/상품사진들/조명/" + product.getImgSname());
					}else if(product.getProductCategoryNo() == 3) {
						file = new File("D:/상품사진들/트리/" + product.getImgSname());
					}else {
						file = new File("D:/상품사진들/기타/" + product.getImgSname());
					}
					mf[i].transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
				productsService.insertImg(product);
			}

		}

		product.setBattach(null);
		return product;
	}

	@GetMapping("/{pid}")
	public Products read(@PathVariable int pid) {
		List<Products> list = productsService.getProductDetail(pid);
		Products product = list.get(0);
		product.setProductPrice(product.getProductPrice().replaceAll(" ", ""));
		return product;
	}
	
	@GetMapping("/readcount")
	public String readCount(int cno) {
		String result;
		if(cno == 1 || cno == 2 || cno ==3 || cno == 4) {
			result = String.valueOf(productsService.getTotalCount(cno));
		}else {
			result = String.valueOf(productsService.getCount());
		}
		return result;
	}

	@GetMapping("/battach/{pid}/{i}")
	public void download(@PathVariable int pid, @PathVariable int i, HttpServletResponse response) {
		try {
			List<ProductsImg> list = productsService.getImgList(pid);
			Products product = productsService.getProductDetail(pid).get(0);
			String battachoname = list.get(i).getImgOname();
			if(battachoname == null) return;
			battachoname = new String(battachoname.getBytes("UTF-8"),"ISO-8859-1");
			String battachsname = list.get(i).getImgSname();      
			String battachspath = "";
			if(product.getProductCategoryNo() == 1) {
				battachspath = "D:/상품사진들/캔들/" + battachsname;
			}else if(product.getProductCategoryNo() == 2) {
				battachspath = "D:/상품사진들/조명/" + battachsname;
			}else if(product.getProductCategoryNo() == 3) {
				battachspath = "D:/상품사진들/트리/" + battachsname;
			}else{
				battachspath = "D:/상품사진들/기타/" + battachsname;
			}
			String battachtype = list.get(i).getImgType();

			response.setHeader("Content-Disposition", "attachment; filename=\""+battachoname+"\";");
			response.setContentType(battachtype);

			InputStream is = new FileInputStream(battachspath);
			OutputStream os = response.getOutputStream();
			FileCopyUtils.copy(is, os);

			is.close();
			os.flush();
			os.close();
		} catch (Exception e) {
			e.printStackTrace();
		}


	}

	@PutMapping("")
	public Products update(Products product) {
		
		product.setProductPrice(product.getProductPrice().replaceAll(",", ""));
		productsService.update(product);

		if(product.getBattach() != null && !(product.getBattach().length == 0)) {
			MultipartFile[] mf = product.getBattach();
			for(int i=0; i<mf.length; i++) {
				product.setImgOname(mf[i].getOriginalFilename());
				product.setImgSname(new Date().getTime() + "-" + mf[i].getOriginalFilename());
				product.setImgType("jpg");
				if(product.getState()[i] == 1) {
					product.setImgState(1);
				}else if(product.getState()[i] == 2){
					product.setImgState(2);
				}else if(product.getState()[i] == 3){
					product.setImgState(3);
				}else if(product.getState()[i] == 4){
					product.setImgState(4);
				}else {
					product.setImgState(5);
				}
				try {
					File file;
					if(product.getProductCategoryNo() == 1) {
						file = new File("D:/상품사진들/캔들/" + product.getImgSname());
					}else if(product.getProductCategoryNo() == 2) {
						file = new File("D:/상품사진들/조명/" + product.getImgSname());
					}else if(product.getProductCategoryNo() == 3) {
						file = new File("D:/상품사진들/트리/" + product.getImgSname());
					}else {
						file = new File("D:/상품사진들/기타/" + product.getImgSname());
					}
					mf[i].transferTo(file);
				} catch (Exception e) {
					e.printStackTrace();
				}
				productsService.updateImg(product);
			}
		}

		product.setBattach(null);
		return product;
	}
	
	
	@GetMapping("/pcount")
	public Map<String, Object> bestMain() {
		
		List<Products> blist = productsService.getBestProductList();
		List<Products> nlist = productsService.getNewProductList();
		
		Map<String, Object> map = new HashMap<>();
		map.put("blist", blist);
		map.put("nlist", nlist); 
		return map;
		
	}

}