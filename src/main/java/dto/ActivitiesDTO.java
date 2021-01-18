/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import entities.Activity;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Benjamin
 */
public class ActivitiesDTO {
            List<ActivityDTO> all = new ArrayList();
        
        public ActivitiesDTO(List<Activity> activityEntities) {
        activityEntities.forEach((a) -> {
            all.add(new ActivityDTO(a));
        });
}

    public List<ActivityDTO> getAll() {
        return all;
    }

}
