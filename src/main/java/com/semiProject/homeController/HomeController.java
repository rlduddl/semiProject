package com.semiProject.homeController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
import com.semiProject.service.StoreService;
import com.semiProject.vo.StoreInfoVO;

@Controller
public class HomeController {

    @Autowired
    private StoreService storeService;

    @GetMapping("/favorite/storeList")
    public String showStoreList(Model model) {
        List<StoreInfoVO> storeList = storeService.getAllStores();
        model.addAttribute("storeList", storeList);
        return "storeList"; // JSP 템플릿 이름
    }
}

