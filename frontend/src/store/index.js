import { createStore } from 'vuex'
import { auth } from './auth.module'
import createPersistedState from 'vuex-persistedstate'

export default createStore({
  state: {
    test: [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
  },
  getters: {
    getSunLevel(state) {
      const sum = state.test[1] + state.test[2] + state.test[3]
      if (sum <= 5) return 0
      else if (sum <= 10) return 1
      else return 2
    },
    getHardLevel(state) {
      const sum = state.test[4] + state.test[5] + state.test[6]
      if (sum <= 5) return 0
      else return 1
    },
    getSizeLevel(state) {
      const sum = state.test[9] + state.test[10] + state.test[11]
      if (sum <= 5) return 0
      else if (sum <= 10) return 1
      else return 2
    },
    getIsEdible(state) {
      if (state.test[7] === 1) return true
      else return false
    },
    getIsToxic(state) {
      if (state.test[8] === 1) return true
      else return false
    }
  },
  mutations: {
    setChoice(state, data) {
      state.test[data.num] = data.choice
    },
    init(state) {
      state.test = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
    }
  },
  actions: {},
  modules: {
    auth
  },
  plugins: [createPersistedState({ storage: window.sessionStorage })]
})
