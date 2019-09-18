package com.telenor.ftv.dbpoc.Controller;

import com.telenor.ftv.dbpoc.repository.DbpocRepository;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
public class DbpocController {

    private DbpocRepository dbpocRepository;

    public DbpocController(DbpocRepository dbpocRepository) {
        this.dbpocRepository = dbpocRepository;
    }

    @PostMapping(path = "/executeProcedure", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<List<Map<String, Object>>> executeProcedure(@RequestParam String name, @RequestParam String args, @RequestParam String user)
            throws SQLException {
        return ResponseEntity.ok().body(dbpocRepository.execProc(name, args, user));
    }

}
