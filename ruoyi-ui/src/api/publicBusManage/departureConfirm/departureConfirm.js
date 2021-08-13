import request from '@/utils/request'

// 查询公交车信息列表
export function listDepartureConfirm(query) {
  return request({
    url: '/departureConfirm/departureConfirm/list',
    method: 'get',
    params: query
  })
}

// 查询公交车信息详细
export function getDepartureConfirm(busid) {
  return request({
    url: '/departureConfirm/departureConfirm/' + busid,
    method: 'get'
  })
}

// 新增公交车信息
export function addDepartureConfirm(data) {
  return request({
    url: '/departureConfirm/departureConfirm',
    method: 'post',
    data: data
  })
}

// 修改公交车信息
export function updateDepartureConfirm(data) {
  return request({
    url: '/departureConfirm/departureConfirm',
    method: 'put',
    data: data
  })
}

// 删除公交车信息
export function delDepartureConfirm(busid) {
  return request({
    url: '/departureConfirm/departureConfirm/' + busid,
    method: 'delete'
  })
}

// 导出公交车信息
export function exportDepartureConfirm(query) {
  return request({
    url: '/departureConfirm/departureConfirm/export',
    method: 'get',
    params: query
  })
}

// 出站确认，新增操作
export function addGoBusConfirm(data) {
  return request({
    url: '/departureConfirm/departureConfirm/addGoBusConfirm',
    method: 'post',
    data: data
  })
}
