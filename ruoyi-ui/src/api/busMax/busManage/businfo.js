import request from '@/utils/request'

// 查询车辆信息列表
export function listBusinfo(query) {
  return request({
    url: '/busManage/businfo/list',
    method: 'get',
    params: query
  })
}

export function getBusscheduling(busid) {
  return request({
    url: '/busManage/businfo/' + busid,
    method: 'get'
  })
}
export function updateBusscheduling(data) {
  return request({
    url: '/busManage/businfo',
    method: 'put',
    data: data
  })
}
export function listBusinfo1(query) {
  return request({
    url: '/busManage/businfo/list1',
    method: 'get',
    params: query
  })
}
export function listBusinfo2(query) {
  return request({
    url: '/busManage/businfo/list2',
    method: 'get',
    params: query
  })
}
export function listBusinfo3(query) {
  return request({
    url: '/busManage/businfo/list3',
    method: 'get',
    params: query
  })
}
export function listBusinfo4(query) {
  return request({
    url: '/busManage/businfo/list4',
    method: 'get',
    params: query
  })
}
export function listBusinfo5(query) {
  return request({
    url: '/busManage/businfo/list5',
    method: 'get',
    params: query
  })
}
// 查询车辆信息详细
export function getBusinfo(busid) {
  return request({
    url: '/busManage/businfo/' + busid,
    method: 'get'
  })
}

// 新增车辆信息
export function addBusinfo(data) {
  return request({
    url: '/busManage/businfo',
    method: 'post',
    data: data
  })
}
export function addBusscheduling(data) {
  return request({
    url: '/busManage/businfo',
    method: 'post',
    data: data
  })
}

// 修改车辆信息
export function updateBusinfo(data) {
  return request({
    url: '/busManage/businfo',
    method: 'put',
    data: data
  })
}

// 删除车辆信息
export function delBusinfo(busid) {
  return request({
    url: '/busManage/businfo/' + busid,
    method: 'delete'
  })
}

// 导出车辆信息
export function exportBusinfo(query) {
  return request({
    url: '/busManage/businfo/export',
    method: 'get',
    params: query
  })
}
