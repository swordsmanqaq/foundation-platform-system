package com.heng.mkt.controller;

import com.heng.auth.annotation.MyPermission;
import com.heng.auth.dto.RoleMenuDTO;
import com.heng.base.utils.LoginContext;
import com.heng.mkt.dto.ClueActivityDto;
import com.heng.mkt.dto.ClueBusinessDto;
import com.heng.mkt.service.IClueService;
import com.heng.mkt.domain.Clue;
import com.heng.mkt.query.ClueQuery;
import com.heng.base.utils.PageList;
import com.heng.base.utils.AjaxResult;
import com.heng.org.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/clue")
public class ClueController {
    @Autowired
    public IClueService clueService;


    /**
     * 保存和修改公用的
     * @param clue 传递的实体
     * @return Ajaxresult转换结果
     */
    @PutMapping
    public AjaxResult addOrUpdate(@RequestBody Clue clue) {
        try {
            if ( clue.getId() != null)
                clueService.update(clue);
            else
                clueService.insert(clue);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败！" + e.getMessage());
        }
    }

    /**
    * 删除对象信息
    * @param id
    * @return
    */
    @DeleteMapping(value = "/{id}")
    public AjaxResult remove(@PathVariable("id") Long id) {
        try {
                clueService.remove(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败！" + e.getMessage());
        }
    }

    /**
     * 批量删除
     * @param ids
     * @return
    */
    @PatchMapping
    public AjaxResult patchRemove(@RequestBody List<Long> ids) {
        try {
                clueService.patchRemove(ids);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("批量删除失败！" + e.getMessage());
        }
    }

    /**
    * 根据id获取
     * @param id
     * @return
    */
    @GetMapping("/{id}")
    public AjaxResult loadById(@PathVariable("id") Long id) {
        try {
            Clue clue =clueService.loadById(id);
            return AjaxResult.me().setResultObj(clue);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取一个失败！" + e.getMessage());
        }
    }


    /**
    * 查看所有的员工信息
    * @return
    */
    @GetMapping
    public AjaxResult loadAll() {

        try {
            List< Clue> list = clueService.loadAll();
            return AjaxResult.me().setResultObj(list);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取所有失败！" + e.getMessage());
        }
    }


    /**
    * 分页查询数据
    *
    * @param query 查询对象
    * @return PageList 分页对象
    */
    @PostMapping
    public AjaxResult pageList(@RequestBody ClueQuery query) {
        try {
            PageList<Clue> pageList = clueService.pageList(query);
            return AjaxResult.me().setResultObj(pageList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取分页数据失败！" + e.getMessage());
        }
    }


    /**
     * 关联活动的提交接口
     * @param dto
     * @return
     */
    @PostMapping("/activity")
    public AjaxResult saveActivity(@RequestBody ClueActivityDto dto){
        try {
            clueService.saveActivity(dto);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("关联活动保存失败");
        }
    }

    /**
     * 关联活动的提交接口
     * @param clue
     * @return
     */
    @PostMapping("/assign")
    public AjaxResult saveClueAssign(@RequestBody Clue clue, HttpServletRequest request){
        try {
            Employee loginUser = LoginContext.getLoginUser(request);
            clueService.saveClueAssign(clue,loginUser);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("菜单保存失败");
        }
    }

    /**
     * 跟进的提交接口
     * @param clue
     * @return
     */
    @PostMapping("/follow")
    public AjaxResult saveClueFollow(@RequestBody Clue clue, HttpServletRequest request){
        try {
            Employee loginUser = LoginContext.getLoginUser(request);
            clueService.saveClueFollow(clue, loginUser);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("菜单保存失败");
        }
    }

    /**
     * 转换商机的提交接口
     * @param dto
     * @return
     */
    @PostMapping("/business")
    public AjaxResult saveClueBusiness(@RequestBody ClueBusinessDto dto, HttpServletRequest request) {
        try {
            Employee loginUser = LoginContext.getLoginUser(request);
            clueService.saveClueBusiness(dto, loginUser);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("菜单保存失败");
        }
    }

    /**
     * 废弃用户信息的提交接口
     * @param clue
     * @return
     */
    @PostMapping("/scrap")
    public AjaxResult clueScrap(@RequestBody Clue clue, HttpServletRequest request) {
        try {
            Employee loginUser = LoginContext.getLoginUser(request);
            clueService.clueScrap(clue,loginUser);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("菜单保存失败");
        }
    }

}
