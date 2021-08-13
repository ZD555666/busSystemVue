import request from '@/utils/request'

// 查询司机工作时间列表
export function listQuantifies(query) {
  return request({
    url: '/quantifies/quantifies/list',
    method: 'get',
    params: query
  })
}

// 查询司机工作时间详细
export function getQuantifies(id) {
  return request({
    url: '/quantifies/quantifies/' + id,
    method: 'get'
  })
}

// 新增司机工作时间
export function addQuantifies(data) {
  return request({
    url: '/quantifies/quantifies',
    method: 'post',
    data: data
  })
}

// 修改司机工作时间
export function updateQuantifies(data) {
  return request({
    url: '/quantifies/quantifies',
    method: 'put',
    data: data
  })
}

// 删除司机工作时间
export function delQuantifies(id) {
  return request({
    url: '/quantifies/quantifies/' + id,
    method: 'delete'
  })
}

// 导出司机工作时间 为params 和 为data 接收参数的注解不同
export function exportQuantifies(query) {
  return request({
    url: '/quantifies/quantifies/export',
    method: 'get',
    params: query
  })
}

// 打卡
export function updateWorkPunchIn(data) {
  return request({
    url: '/quantifies/quantifies/updateWorkPunchIn',
    method: 'post',
    data: data
  })
}
