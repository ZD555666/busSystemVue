import request from '@/utils/request'

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