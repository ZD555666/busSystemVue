import request from '@/utils/request'

// 查询收银统计列表
export function listCashier(query) {
  return request({
    url: '/road/cashier/list',
    method: 'get',
    params: query
  })
}

// 查询收银统计详细
export function getCashier(cashierid) {
  return request({
    url: '/road/cashier/' + cashierid,
    method: 'get'
  })
}

// 新增收银统计
export function addCashier(data) {
  return request({
    url: '/road/cashier',
    method: 'post',
    data: data
  })
}

// 修改收银统计
export function updateCashier(data) {
  return request({
    url: '/road/cashier',
    method: 'put',
    data: data
  })
}

// 删除收银统计
export function delCashier(cashierid) {
  return request({
    url: '/road/cashier/' + cashierid,
    method: 'delete'
  })
}

// 导出收银统计
export function exportCashier(query) {
  return request({
    url: '/road/cashier/export',
    method: 'get',
    params: query
  })
}