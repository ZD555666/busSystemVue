import request from '@/utils/request'

// 查询站点客流统计列表
export function listRidership(query) {
  return request({
    url: '/road/ridership/list',
    method: 'get',
    params: query
  })
}

// 查询站点客流统计详细
export function getRidership(ridershipid) {
  return request({
    url: '/road/ridership/' + ridershipid,
    method: 'get'
  })
}

// 新增站点客流统计
export function addRidership(data) {
  return request({
    url: '/road/ridership',
    method: 'post',
    data: data
  })
}

// 修改站点客流统计
export function updateRidership(data) {
  return request({
    url: '/road/ridership',
    method: 'put',
    data: data
  })
}

// 删除站点客流统计
export function delRidership(ridershipid) {
  return request({
    url: '/road/ridership/' + ridershipid,
    method: 'delete'
  })
}

// 导出站点客流统计
export function exportRidership(query) {
  return request({
    url: '/road/ridership/export',
    method: 'get',
    params: query
  })
}