package com.spring.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.spring.springcloud.entities.Dept;
import com.spring.springcloud.service.DeptService;
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
    @HystrixCommand(fallbackMethod = "processHystrix_Get")
    public Dept get(@PathVariable Long id){
        Dept dept = this.deptService.get(id);
        if(null == dept){
            System.out.println("跑到异常里面了");
            throw new RuntimeException("该id"+id+"没有对应的信息，null--@HystrixCommand");
        }
        return dept;
    }

    public Dept processHystrix_Get(@PathVariable("id") Long id){
        Dept dept = new Dept();
        dept.setDeptno(id);
        dept.setDname("该id"+id+"没有对应的信息，null--@HystrixCommand");
        dept.setDb_source("");
        return dept;
    }

    @RequestMapping(value = "/dept/list")
    public List<Dept> list(){
        return deptService.list();
    }


}
