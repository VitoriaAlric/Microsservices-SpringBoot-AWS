//package br.com.vigcfdev.aws_project01.controller;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/test")
//public class TestController {
//    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);
//
//    @GetMapping("/project/{name}")
//    public ResponseEntity<?> projectTest(@PathVariable String name) {
//        LOG.info("Test controller - name: {}", name);
//
//        return ResponseEntity.ok("Name: " + name);
//    }
//
//    @GetMapping("/project/Textcolor")
//    public ResponseEntity<?> projectColor() {
//        LOG.info("Test controller - Always black!");
//
//        return ResponseEntity.ok("Always black!");
//    }
//
//    @GetMapping("/project/Textcolor")
//    public ResponseEntity<?> projectColor2() {
//        LOG.info("Test controller - Always red!");
//
//        return ResponseEntity.ok("Always red!");
//    }
//}