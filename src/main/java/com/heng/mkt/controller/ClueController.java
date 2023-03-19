package com.heng.mkt.controller;

import com.heng.auth.annotation.MyPermission;
import com.heng.auth.dto.RoleMenuDTO;
import com.heng.base.utils.LoginContext;
import com.heng.mkt.domain.Activity;
import com.heng.mkt.dto.*;
import com.heng.mkt.service.IClueService;
import com.heng.mkt.domain.Clue;
import com.heng.mkt.query.ClueQuery;
import com.heng.base.utils.PageList;
import com.heng.base.utils.AjaxResult;
import com.heng.org.domain.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

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
     * 获取所有市场活动
     * @param typeId
     * @return
     */
    @GetMapping("/type/{typeId}")
    public AjaxResult getActivitys(@PathVariable Long typeId) {
        try {
            List<Activity> activityList = clueService.getActivitys(typeId);
            return AjaxResult.me().setResultObj(activityList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("获取所有失败！" + e.getMessage());
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
     * 分配的提交接口
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

    /**
     * 查询饼状图线索的数据值
     * @return
     */
    @PostMapping("/data")
    public AjaxResult getDrawPieChartData(){
        try {
            List<ClueDataDTO> clueDataList = clueService.getDrawPieChartData();
            return AjaxResult.me().setResultObj(clueDataList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("查询失败");
        }
    }

    /**
     * 获取折线图的数据
     * @return
     */
    @PostMapping("/chart")
    public AjaxResult getDrawLineChart(){
        try {
            List<LineChartDTO> lineChartList = clueService.getDrawLineChart();
            return AjaxResult.me().setResultObj(lineChartList);
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("查询失败");
        }
    }


    /**
     * 导入excel文件
     * @param file
     * @return
     */
    @PostMapping("/importExcel")
    public AjaxResult importExcel(@RequestParam("file") MultipartFile file){
        try {
            clueService.importExcel(file);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("导入失败");
        }
    }

    /**
     * 导出excel无keyword
     * @param param
     * @param response
     * @return
     */
    @GetMapping("/exportExcel")
    public AjaxResult exportExcel(Map<String,Object> param, HttpServletResponse response){
        try {
            clueService.exportExcel(param,response);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("导出失败");
        }
    }

    /**
     * 导出excel有keyword
     * @param param
     * @param response
     * @return
     */
    @GetMapping("/exportExcel/{keyword}")
    public AjaxResult exportExcelByKeyword(@PathVariable("keyword") String keyword, Map<String,Object> param, HttpServletResponse response){
        try {
            clueService.exportExcelByKeyword(keyword,param,response);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("导出失败");
        }
    }

}