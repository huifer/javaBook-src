package com.huifer.webflux.controller;

import com.huifer.webflux.entity.Student;
import com.huifer.webflux.repository.StudentRepository;
import com.huifer.webflux.util.NameValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

/**
 * 描述:
 *
 * @author huifer
 * @date 2019-06-02
 */
@RestController
@RequestMapping("/student")
public class StudentController {


    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/all")
    public Flux<Student> all() {
        return studentRepository.findAll();
    }

    @GetMapping(value = "/sse/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Student> allSSE() {
        return studentRepository.findAll();
    }

    @PostMapping("/update")
    public ResponseEntity add(@Valid Student student) {
        return ResponseEntity.ok(studentRepository.save(student));
    }


    @PostMapping("/save")
    public ResponseEntity save(@Valid Student student) {
        NameValidateUtil.validateName(student.getName());

        return ResponseEntity.ok(studentRepository.save(student));
    }


    @DeleteMapping("/del/{id}")
    public Mono<Void> deleteStudentVoid(@PathVariable(value = "id") String id) {
        // 无状态删除 不管是否成功 都是200
        return studentRepository.deleteById(id);
    }

    @DeleteMapping("/ddl/{id}")
    public Mono<ResponseEntity> deleteStudent(@PathVariable(value = "id") String id) {
        Mono<ResponseEntity> responseEntityMono = studentRepository.findById(id).flatMap(
                student -> studentRepository.delete(student)
                        // 成功
                        .then(Mono.just(ResponseEntity.ok().body(student)))
                        // 失败
                        .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND))
        );

        return responseEntityMono;
    }


    @PutMapping("/put/{id}")
    public Mono<ResponseEntity<Student>> update(@PathVariable(value = "id") String id, @RequestBody Student student) {

        Mono<ResponseEntity<Student>> responseEntityMono = studentRepository.findById(id).flatMap(
                stu -> {
                    stu.setAge(student.getAge());
                    stu.setName(student.getName());
                    return studentRepository.save(stu);
                }).map(stu -> new ResponseEntity<Student>(stu, HttpStatus.OK))

                .defaultIfEmpty(new ResponseEntity<Student>(HttpStatus.NOT_FOUND));


        return responseEntityMono;
    }


    @GetMapping("/find/{id}")
    public Mono<ResponseEntity<Student>> findById(@PathVariable("id") String id) {

        return studentRepository.findById(id).map(stu -> new ResponseEntity<Student>(stu, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<Student>(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/age/{below}/{top}")
    public Flux<Student> findByAge(@PathVariable("below") int below, @PathVariable("top") int top) {
        return studentRepository.findByAgeBetween(below, top);
    }


}
