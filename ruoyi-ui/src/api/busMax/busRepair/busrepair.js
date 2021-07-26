import request from '@/utils/request'

// 查询车辆维修列表
export function listBusrepair(query) {
  return request({
    url: '/busRepair/busrepair/list',
    method: 'get',
    params: query
  })
}

// 查询车辆维修详细
export function getBusrepair(repairid) {
  return request({
    url: '/busRepair/busrepair/' + repairid,
    method: 'get'
  })
}

// 新增车辆维修
export function addBusrepair(data) {
  return request({
    url: '/busRepair/busrepair',
    method: 'post',
    data: data
  })
}

// 修改车辆维修
export function updateBusrepair(data) {
  return request({
    url: '/busRepair/busrepair',
    method: 'put',
    data: data
  })
}

// 删除车辆维修
export function delBusrepair(repairid) {
  return request({
    url: '/busRepair/busrepair/' + repairid,
    method: 'delete'
  })
}

// 导出车辆维修
export function exportBusrepair(query) {
  return request({
    url: '/busRepair/busrepair/export',
    method: 'get',
    params: query
  })
}
