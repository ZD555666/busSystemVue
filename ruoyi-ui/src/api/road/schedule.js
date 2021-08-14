import request from '@/utils/request'

//获取所有线路的编号
export function getAllBusNo() {
  return request({
    url: '/road/schedule/getAllBusNo',
    method: 'get'
    // params: param,
    // config: {headers: {'Content-Type': 'application/json;charset=UTF-8'}}
  })
}

//一条线路添加多个时间段
export function addManyTimes(param) {
  return request({
    url: '/road/schedule/addManyTimes',
    method: 'get',
    params: param,
    config: {headers: {'Content-Type': 'application/json;charset=UTF-8'}}
  })
}

// 查询线路发车时刻配置列表
export function listSchedule(query) {
  return request({
    url: '/road/schedule/list',
    method: 'get',
    params: query
  })
}

// 查询线路发车时刻配置详细
export function getSchedule(scheduleId) {
  return request({
    url: '/road/schedule/' + scheduleId,
    method: 'get'
  })
}

// 新增线路发车时刻配置
export function addSchedule(data) {
  return request({
    url: '/road/schedule',
    method: 'post',
    data: data
  })
}

// 修改线路发车时刻配置
export function updateSchedule(data) {
  return request({
    url: '/road/schedule',
    method: 'put',
    data: data
  })
}

// 删除线路发车时刻配置
export function delSchedule(scheduleId) {
  return request({
    url: '/road/schedule/' + scheduleId,
    method: 'delete'
  })
}

// 导出线路发车时刻配置
export function exportSchedule(query) {
  return request({
    url: '/road/schedule/export',
    method: 'get',
    params: query
  })
}
