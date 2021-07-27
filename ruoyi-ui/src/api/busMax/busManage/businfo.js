import request from '@/utils/request'

// 查询车辆信息列表
export function listBusinfo(query) {
  return request({
    url: '/busManage/businfo/list',
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