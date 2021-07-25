import request from '@/utils/request'

// 查询城市配置列表
export function listCity(query) {
  return request({
    url: '/road/city/list',
    method: 'get',
    params: query
  })
}

// 查询城市配置详细
export function getCity(cityid) {
  return request({
    url: '/road/city/' + cityid,
    method: 'get'
  })
}

// 新增城市配置
export function addCity(data) {
  return request({
    url: '/road/city',
    method: 'post',
    data: data
  })
}

// 修改城市配置
export function updateCity(data) {
  return request({
    url: '/road/city',
    method: 'put',
    data: data
  })
}

// 删除城市配置
export function delCity(cityid) {
  return request({
    url: '/road/city/' + cityid,
    method: 'delete'
  })
}

// 导出城市配置
export function exportCity(query) {
  return request({
    url: '/road/city/export',
    method: 'get',
    params: query
  })
}