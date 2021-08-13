import request from '@/utils/request'

// 查询车辆维修上报列表
export function listBusFaultReport(query) {
  return request({
    url: '/busFaultReport/busFaultReport/list',
    method: 'get',
    params: query
  })
}

// 查询车辆维修上报详细
export function getBusFaultReport(repairid) {
  return request({
    url: '/busFaultReport/busFaultReport/' + repairid,
    method: 'get'
  })
}

// 新增车辆维修上报
export function addBusFaultReport(data) {
  return request({
    url: '/busFaultReport/busFaultReport',
    method: 'post',
    data: data
  })
}

// 修改车辆维修上报
export function updateBusFaultReport(data) {
  return request({
    url: '/busFaultReport/busFaultReport',
    method: 'put',
    data: data
  })
}

// 删除车辆维修上报
export function delBusFaultReport(repairid) {
  return request({
    url: '/busFaultReport/busFaultReport/' + repairid,
    method: 'delete'
  })
}

// 导出车辆维修上报
export function exportBusFaultReport(query) {
  return request({
    url: '/busFaultReport/busFaultReport/export',
    method: 'get',
    params: query
  })
}