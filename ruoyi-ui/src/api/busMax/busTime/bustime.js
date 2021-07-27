import request from '@/utils/request'

// 查询公交车时刻列表
export function listBustime(query) {
  return request({
    url: '/busTime/bustime/list',
    method: 'get',
    params: query
  })
}

// 查询公交车时刻详细
export function getBustime(scheduleid) {
  return request({
    url: '/busTime/bustime/' + scheduleid,
    method: 'get'
  })
}

// 新增公交车时刻
export function addBustime(data) {
  return request({
    url: '/busTime/bustime',
    method: 'post',
    data: data
  })
}

// 修改公交车时刻
export function updateBustime(data) {
  return request({
    url: '/busTime/bustime',
    method: 'put',
    data: data
  })
}

// 删除公交车时刻
export function delBustime(scheduleid) {
  return request({
    url: '/busTime/bustime/' + scheduleid,
    method: 'delete'
  })
}

// 导出公交车时刻
export function exportBustime(query) {
  return request({
    url: '/busTime/bustime/export',
    method: 'get',
    params: query
  })
}