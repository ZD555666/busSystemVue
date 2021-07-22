import request from '@/utils/request'

// 查询详细信息列表
export function listBusinfo(query) {
  return request({
    url: '/bus/businfo/list',
    method: 'get',
    params: query
  })
}

// 查询详细信息详细
export function getBusinfo(busid) {
  return request({
    url: '/bus/businfo/' + busid,
    method: 'get'
  })
}

// 新增详细信息
export function addBusinfo(data) {
  return request({
    url: '/bus/businfo',
    method: 'post',
    data: data
  })
}

// 修改详细信息
export function updateBusinfo(data) {
  return request({
    url: '/bus/businfo',
    method: 'put',
    data: data
  })
}

// 删除详细信息
export function delBusinfo(busid) {
  return request({
    url: '/bus/businfo/' + busid,
    method: 'delete'
  })
}

// 导出详细信息
export function exportBusinfo(query) {
  return request({
    url: '/bus/businfo/export',
    method: 'get',
    params: query
  })
}