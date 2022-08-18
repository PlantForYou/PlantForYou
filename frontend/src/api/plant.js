import http from './http'

export function getPlant(plantId) {
  return http.get(`/api/plants/${plantId}`)
}
export function getPlantList() {
  return http.get('/api/plants')
}
export function getPlantQues(plantQuesId) {
  return http.get(`/api/plants/questions/${plantQuesId}`)
}
export function getResult(params) {
  return http.get('/api/plants/mbti', { params: params })
}
