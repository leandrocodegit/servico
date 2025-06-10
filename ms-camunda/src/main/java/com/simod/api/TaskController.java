package com.simod.api;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.UriInfo;
import org.camunda.bpm.engine.IdentityService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.identity.Group;
import org.camunda.bpm.engine.rest.dto.CountResultDto;
import org.camunda.bpm.engine.rest.dto.identity.GroupDto;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.camunda.bpm.engine.rest.dto.task.TaskQueryDto;
import org.camunda.bpm.engine.rest.sub.task.TaskReportResource;
import org.camunda.bpm.engine.rest.sub.task.TaskResource;
import org.camunda.bpm.engine.task.Task;
import org.camunda.bpm.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDto>> listaTask(@RequestHeader("X-Tenant-Id") String tenantId){
        List<Task> tasks = taskService
                .createTaskQuery()
                .tenantIdIn(tenantId)
                .list();
        return ResponseEntity.ok(tasks.stream()
                .map(TaskDto::fromEntity).toList());
    }

    @GetMapping("/{id}")
    ResponseEntity<TaskDto> getTask(@RequestHeader("X-Tenant-Id") String tenantId, @PathVariable("id") String id) {
        Task task = taskService.createTaskQuery()
                .taskId(id)
                .tenantIdIn(tenantId)
                .singleResult();
        return ResponseEntity.ok(TaskDto.fromEntity(task));
    }


    @GetMapping("/count")
    ResponseEntity<CountResultDto> getTasksCount(@RequestHeader("X-Tenant-Id") String tenantId, @RequestHeader("X-User-Id") String userId) {
        Long count = taskService.createTaskQuery()
                .taskCandidateUser(userId)
                .tenantIdIn(tenantId)
                .count();
        return ResponseEntity.ok(new CountResultDto(count));
    }

    @POST
    @Path("/count")
    @Consumes({"application/json"})
    @Produces({"application/json"})
    CountResultDto queryTasksCount(TaskQueryDto var1) {

        return null;
    }

    @POST
    @Path("/create")
    @Consumes({"application/json"})
    void createTask(TaskDto var1) {

    }

    @Path("/report")
    TaskReportResource getTaskReportResource() {

        return null;
    }
}
