package edu.mx.utleon.militarizedcollegesystem.microservices.academics.academics;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.GroupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupController {

    @Autowired
    private GroupService groupService;

    @GetMapping("/groups")
    public List<GroupDto> getAllGroups(@RequestParam(required = false) Long periodId, @RequestParam(required = false) Long careerId) {
        if (periodId != null && careerId != null) {
            return groupService.getAllGroupsByPeriodIdAndCareerId(periodId, careerId);
        }
        return groupService.getAllGroups();
    }

    @GetMapping("/group")
    public GroupDto getGroup(@RequestParam(required = false) Long id) {
        return groupService.getGroupById(id);
    }

    @PostMapping("/groups")
    public GroupDto createGroup(@RequestBody GroupDto group) {
        return groupService.createGroup(group);
    }

}
