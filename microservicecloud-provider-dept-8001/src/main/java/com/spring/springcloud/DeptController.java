package com.spring.springcloud;

import com.spring.springcloud.entities.Dept;
import com.spring.springcloud.service.DeptService;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;
    @Autowired
    private DiscoveryClient client;

    @RequestMapping(value = "/dept/discovery",method = RequestMethod.GET)
    public Object discovery(){
        List<String> list = client.getServices();
        System.out.println("service====="+list);
        List<ServiceInstance> srvList = client.getInstances("MICROSERVICECLOUD-DEPT");
        for (ServiceInstance elment :srvList) {
            System.out.println("serviceId="+elment.getServiceId()
             +"\t"+"host="+elment.getHost()
             +"\t"+"port="+elment.getPort()
             +"\t"+"url="+elment.getUri());
        }
        return this.client;
    }

    @RequestMapping(value = "/dept/add")
    public boolean add(@RequestBody Dept dept){
        return deptService.add(dept);
    }
    @RequestMapping(value = "/dept/get/{id}")
    public Dept get(@PathVariable Long id){
        return deptService.get(id);
    }

    @RequestMapping(value = "/dept/list")
    public List<Dept> list(){
        return deptService.list();
    }


}
