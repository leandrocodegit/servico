package com.simod.api;

import org.camunda.bpm.ProcessEngineService;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.rest.dto.repository.ProcessDefinitionDto;
import org.camunda.bpm.engine.rest.dto.task.TaskDto;
import org.camunda.bpm.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class ProcessDefinitionController {

    @Autowired
    private ProcessEngine processEngineService;

    @GetMapping
    public ResponseEntity<List<ProcessDefinitionDto>> listaTask(@RequestHeader("X-Tenant-Id") String tenantId){
        ProcessDefinition processDefinition = (ProcessDefinition) this.processEngineService()..getRepositoryService().createProcessDefinitionQuery().processDefinitionKey(processDefinitionKey).tenantIdIn(new String[]{tenantId}).latestVersion().singleResult();

        List<ProcessDefinitionEntity> processDefinitionEntities = processEngineService
                .getProcessEngine()
                .tenantIdIn(tenantId)
                .list();
        return ResponseEntity.ok(processDefinitionEntities.stream()
                .map(ProcessDefinitionDto::fromProcessDefinition).toList());
    }
}
