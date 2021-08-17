import request from '@/utils/request'

// 查询busscheduling列表
export function listBusscheduling(query) {
  return request({
    url: '/busScheduling/busscheduling/list',
    method: 'get',
    params: query
  })
}
export function listBusscheduling1(query) {
  return request({
    url: '/busScheduling/busscheduling/list1',
    method: 'get',
    params: query
  })
}
export function listBusscheduling2(query) {
  return request({
    url: '/busScheduling/busscheduling/list2',
    method: 'get',
    params: query
  })
}

// 查询busscheduling详细
export function getBusscheduling(busid) {
  return request({
    url: '/busScheduling/busscheduling/' + busid,
    method: 'get'
  })
}

// 新增busscheduling
export function addBusscheduling(data) {
  return request({
    url: '/busScheduling/busscheduling',
    method: 'post',
    data: data
  })
}

// 修改busscheduling
export function updateBusscheduling(data) {
  return request({
    url: '/busScheduling/busscheduling',
    method: 'put',
    data: data
  })
}

// 删除busscheduling
export function delBusscheduling(busid) {
  return request({
    url: '/busScheduling/busscheduling/' + busid,
    method: 'delete'
  })
}

// 导出busscheduling
export function exportBusscheduling(query) {
  return request({
    url: '/busScheduling/busscheduling/export',
    method: 'get',
    params: query
  })
}
