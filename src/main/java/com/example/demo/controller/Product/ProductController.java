package com.example.demo.controller.Product;


import com.example.demo.SessionConst;
import com.example.demo.entity.Members;
import com.example.demo.dto.ProductFormDto;
import com.example.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.net.MalformedURLException;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/addProduct")
    public String addProduct(@ModelAttribute("productData") ProductFormDto productData){
        return "addProductForm";
    }

    @PostMapping("/addProduct")
    public String saveProduct(
                              @ModelAttribute("productData") ProductFormDto productData,
                              HttpServletRequest request,
                              BindingResult bindingResult) throws IOException {

        log.info("multipartFile={}", productData.getImageFileList());

        if(bindingResult.hasGlobalErrors()){
            bindingResult.reject("productErr","물품을 입력하세요");
            return "addProductForm";
        }

        HttpSession session = request.getSession();
        Members loginMember = (Members) session.getAttribute(SessionConst.LOGIN_MEBMER);


        productService.save(productData ,loginMember);

        return "redirect:/";
    }

    /**
     *
     * @param filename 실제 파일명
     * @return 파일 시스템에서 찾은 파일 반환
     * @throws MalformedURLException
     */
    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws
            MalformedURLException {
        return new UrlResource("file:" + productService.getFullPath(filename));
    }


}
