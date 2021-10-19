package com.example.backend.Controller;

import com.example.backend.Service.Impl.QaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class QaController {
    private final QaService qaService;

    public QaController(QaService qaService) {
        this.qaService = qaService;
    }

    @PostMapping("/getAnswer")
    public ResponseEntity<Map<String, String>> getAnswer(@Valid @RequestBody Map<String, Object> body) {
        System.out.println(body.toString());
        int questionIndex = (int) body.get("questionIndex");
        String groupName = (String) body.get("groupName");
        String brandName = (String) body.get("brandName");
        String rankName = (String) body.get("rankName");
        String vipName = (String) body.get("vipName");

        Map<String, String> response = new HashMap<>();
        response.put("answer", qaService.getAnswer(questionIndex, groupName, brandName, rankName, vipName));
        return ResponseEntity.ok().body(response);
    }
}
