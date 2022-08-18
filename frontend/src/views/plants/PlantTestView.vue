<template>
  <div>
    <TheDarkHeader></TheDarkHeader>

    <div style="padding: 5% 10%">
      <div>
        <h3>{{ plantQues.question }}</h3>
        <li>
          <ul
            @click="setChoice(option.score)"
            :key="option.plantQuesOptionId"
            v-for="option in plantQues.optionList"
            style="cursor: pointer"
          >
            {{
              option.option
            }}
          </ul>
        </li>
      </div>
      <button v-if="plantQuesId !== 1" @click.prevent="prev">이전</button>
    </div>
  </div>
</template>

<script>
import TheDarkHeader from '@/layouts/TheDarkHeader.vue'
import * as plantApi from '@/api/plant'

export default {
  components: {
    TheDarkHeader
  },
  data() {
    return {
      plantQuesId: parseInt(this.$route.params.id),
      plantQues: {}
    }
  },
  created() {
    plantApi.getPlantQues(this.plantQuesId).then((res) => {
      this.plantQues = res.data
    })
  },
  methods: {
    prev() {
      location.href = '/plants/test/' + (this.plantQuesId - 1)
    },
    setChoice(choice) {
      this.$store.commit('setChoice', { num: this.plantQuesId, choice: choice })
      if (this.plantQuesId !== 11) {
        location.href = '/plants/test/' + (this.plantQuesId + 1)
      } else {
        this.$router.push({
          name: 'PlantTestResultView'
        })
      }
    }
  }
}
</script>

<style scoped>
.selected {
  font-weight: bold;
}
</style>
