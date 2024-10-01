package edu.mx.utleon.militarizedcollegesystem.microservices.academics.academics;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.GroupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/groups")
    public List<GroupDto> getAllGroups() {
        return groupService.getAllGroups();
    }

    @PostMapping("/groups")
    public GroupDto createGroup(@RequestBody GroupDto group) {
        return groupService.createGroup(group);
    }

}
