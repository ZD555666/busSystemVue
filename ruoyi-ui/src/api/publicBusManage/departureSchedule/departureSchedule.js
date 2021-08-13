import request from '@/utils/request'

// 查询发车时刻表列表
export function listDepartureSchedule(query) {
  return request({
    url: '/drivermanager/departureSchedule/list',
    method: 'get',
    params: query
  })
}

// 查询发车时刻表详细
export function getDepartureSchedule(scheduleid) {
  return request({
    url: '/drivermanager/departureSchedule/' + scheduleid,
    method: 'get'
  })
}

// 新增发车时刻表
export function addDepartureSchedule(data) {
  return request({
    url: '/drivermanager/departureSchedule',
    method: 'post',
    data: data
  })
}

// 修改发车时刻表
export function updateDepartureSchedule(data) {
  return request({
    url: '/drivermanager/departureSchedule',
    method: 'put',
    data: data
  })
}

// 删除发车时刻表
export function delDepartureSchedule(scheduleid) {
  return request({
    url: '/drivermanager/departureSchedule/' + scheduleid,
    method: 'delete'
  })
}

// 导出发车时刻表
export function exportDepartureSchedule(query) {
  return request({
    url: '/drivermanager/departureSchedule/export',
    method: 'get',
    params: query
  })
}

//生成时间格式
export function getHMS(time) {
  const hour = parseInt(time / 3600) < 10 ? '0' + parseInt(time / 3600) : parseInt(time / 3600)
  const min = parseInt(time % 3600 / 60) < 10 ? '0' + parseInt(time % 3600 / 60) : parseInt(time % 3600 / 60)
  const sec = parseInt(time % 3600 % 60) < 10 ? '0' + parseInt(time % 3600 % 60) : parseInt(time % 3600 % 60)
  return hour + ':' + min + ':' + sec
}

