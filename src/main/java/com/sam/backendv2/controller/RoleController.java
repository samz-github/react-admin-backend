package com.sam.backendv2.controller;

import com.sam.backendv2.dao.RoleDao;
import com.sam.backendv2.entity.Role;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("api")
@RestController
public class RoleController {

    @Autowired
    RoleDao roleDao;

    /*
     * field用于排序order by field,
     * order包含ASC和DESC，曾序和降序
     * filter = {"username": ""}，用于SQL过滤
     */
    @GetMapping("role")
    public List<Role> getRoles(@RequestParam("field") String field,
                               @RequestParam("order") String order,
                               @RequestParam("filter") String filter,
                               @RequestParam("offset") int offset,
                               @RequestParam("rowcount") int rowcount,
                               HttpServletResponse response){
        //limitRoleList是通过SQL语句的limit来进行分页显示
        List<Role> limitRoleList;
        //计算匹配到的记录的总数
        Integer countList;
        //通过JSONObject函数，把输入的json字符串转换为Java的JSON对象
        JSONObject jsonpObject = new JSONObject(filter);
        // 判断jsonObject是否存在username这个key
        if (jsonpObject.isNull("username")) {
            limitRoleList = roleDao.selectAllLimit(field,order,offset,rowcount);
            countList = roleDao.countAll();
        } else {
            String username = jsonpObject.getString("username");
            //在SQL语句中通过like %username%进行模糊匹配
            String percentUsernamePercent = "%" + username + "%";
            limitRoleList = roleDao.selectAllByUsernameLimit(percentUsernamePercent,field,order,offset,rowcount);
            countList = roleDao.countByUsername(percentUsernamePercent);
        }
        //往HTTP响应头添加: "Content-Range": 总数
        response.setHeader("Content-Range", Integer.toString(countList));
       return  limitRoleList;
    }

    @GetMapping("role/{id}")
    public Role getRoleById(@PathVariable("id") int id) {
        Role role = roleDao.selectAllById(id);
        return role;
    }

    @PutMapping("role/{id}")
    public Role updateRoleById(@PathVariable("id") int id, @RequestBody Role role){
       roleDao.updateRecordById(role.getUsername(),role.getRoleName(), role.getId());
       return role;
    }


    @DeleteMapping("role/{id}")
    public void deleteById(@PathVariable int id) {
        roleDao.deleteRecordById(id);
    }


    @PostMapping("role")
    public Role createRole(@RequestBody Role role) {
        roleDao.createRecord(role.getUsername(), role.getRoleName());
        return roleDao.selectAllByUsername(role.getUsername());
    }
}
