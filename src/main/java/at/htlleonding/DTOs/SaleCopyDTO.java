package at.htlleonding.DTOs;


import java.util.ArrayList;
import java.util.List;

public class SaleCopyDTO {

    private Integer sale_id;

    private List<Integer> copyIdList = new ArrayList<Integer>();

    public List<Integer> getCopyIdList() {
        return copyIdList;
    }

    public Integer getSale_id() {
        return sale_id;
    }
}
