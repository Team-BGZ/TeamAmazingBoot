package com.example.demo.util;

import com.example.demo.pojo.Organization;
import io.netty.util.internal.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2020/4/20.
 */


@Component
public class ListToTreeUtil {




        public List listToTree(List<Organization> utilList){
            List<Organization> returnList=new ArrayList();

            for(int i=0;i<utilList.size();i++){
                if(StringUtils.isEmpty(utilList.get(i).getOrganizationUpId())){
                    returnList.add(utilList.get(i));
                }
                for(Organization o : utilList){
                    if(o.getOrganizationUpId()==utilList.get(i).getId()){
                        if(utilList.get(i).getOrganizationSon()==null){
                            utilList.get(i).setOrganizationSon(new ArrayList<Organization>());
                        }
                        utilList.get(i).getOrganizationSon().add(o);
                    }
                }
            }

            return returnList;
        }


}
