package com.example.backend.Controller;

import com.example.backend.Service.IMyCoinService;
import com.example.backend.Service.Impl.BrandService;
import com.example.backend.Service.Impl.GroupService;
import com.example.backend.Service.Impl.QaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class MyCoinController {
    private final IMyCoinService myCoinService;

    private final GroupService groupService;

    private final BrandService brandService;

    private final QaService qaService;

    public MyCoinController(IMyCoinService myCoinService, GroupService groupService, BrandService brandService, QaService qaService) {
        this.myCoinService = myCoinService;
        this.groupService = groupService;
        this.brandService = brandService;
        this.qaService = qaService;
    }

    @GetMapping("/getCoin")
    public ResponseEntity<Map<String, Object>> getCoin() {
        Map<String, Object> response = myCoinService.getCoin();
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/getGroupNameList")
    public ResponseEntity<List<String>> getGroupNameList() {
        return ResponseEntity.ok().body(groupService.getGroupNameList());
    }

    @PostMapping("/getGraphByGroupName")
    public ResponseEntity<Map<String, Object>> getGraphByGroupName(@Valid @RequestBody Map<String, Object> body) {
        String name = (String) body.get("groupName");
        Map<String, Object> response = groupService.getGraphByGroupName3Level(name);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/getDetailByBrandName")
    public ResponseEntity<Map<String, Object>> getDetailByBrandName(@Valid @RequestBody Map<String, Object> body) {
        String name = (String) body.get("brandName");
        System.out.println("getDetailByBrandName " + name);
        Map<String, Object> response = new HashMap<>();
        response.put("detail", qaService.getDetailByBrandName(name));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/getDetailByGroupName")
    public ResponseEntity<Map<String, Object>> getDetailByGroupName(@Valid @RequestBody Map<String, Object> body) {
        String name = (String) body.get("groupName");
        System.out.println("getDetailByGroupName " + name);
        Map<String, Object> response = new HashMap<>();
        response.put("detail", groupService.getDetailByGroupName(name));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/getPicByBrandName")
    public ResponseEntity<Map<String, Object>> getPicByBrandName(@Valid @RequestBody Map<String, Object> body) {
        String name = (String) body.get("brandName");
        Map<String, Object> response = new HashMap<>();
        response.put("detail", brandService.getLogoUrlByBrandName(name));
        return ResponseEntity.ok().body(response);
    }
}