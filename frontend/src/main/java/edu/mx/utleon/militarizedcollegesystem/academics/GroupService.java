package edu.mx.utleon.militarizedcollegesystem.academics;

import edu.mx.utleon.militarizedcollegesystem.common.dtos.GroupDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${microservices.url.academics}")
    private String ACADEMICS_URL;

    public List<GroupDto> getAllGroups() {
        String path = ACADEMICS_URL + "groups";
        return restTemplate.getForObject(path, List.class);
    }

    public void createGroup(GroupDto group) {
        String path = ACADEMICS_URL + "groups";
        restTemplate.postForObject(path, group, GroupDto.class);
    }

    public List<GroupDto> getAllGroupsByPeriodIdAndCareerId(Long periodId, Long careerId) {
        String path = ACADEMICS_URL + "groups?periodId=" + periodId + "&careerId=" + careerId;
        return restTemplate.getForObject(path, List.class);
    }

    public GroupDto getGroupById(Long id) {
        String path = ACADEMICS_URL + "group?id=" + id;
        return restTemplate.getForObject(path, GroupDto.class);
    }
}
