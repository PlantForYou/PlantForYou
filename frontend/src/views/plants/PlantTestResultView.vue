<template>
  <div>
    <TheDarkHeader></TheDarkHeader>

    <div style="padding: 5% 10%">
      <div class="d-flex flex-column gap-5" style="text-align: center">
        <h2 style="font-weight: bold">당신의 운명의 반려식물은?</h2>
        <div class="d-flex flex-row justify-content-center">
          <img
            :src="result.imgUrl"
            class="bd-placeholder-img rounded-circle"
            width="330"
            height="330"
            role="img"
            alt="식물 사진"
            preserveAspectRatio="xMidYMid slice"
            focusable="false"
          />
        </div>
        <h3>
          <strong>'{{ result.name }}'</strong> 입니다.
        </h3>
        <div>
          <button
            class="btn btn-outline-dark"
            style="font-weight: bold; outline: 1px solid black"
            @click="begin"
          >
            다시하기
          </button>
          &nbsp;&nbsp;&nbsp;
          <button
            class="btn btn-outline-dark"
            style="font-weight: bold; outline: 1px solid black"
            @click="goPlantDetail(result.plantDictId)"
          >
            식물 설명 보기
          </button>
        </div>
      </div>
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
      sunLevel: this.$store.getters.getSunLevel,
      hardLevel: this.$store.getters.getHardLevel,
      sizeLevel: this.$store.getters.getSizeLevel,
      isEdible: this.$store.getters.getIsEdible,
      isToxic: this.$store.getters.getIsToxic,
      result: {}
    }
  },
  created() {
    plantApi
      .getResult({
        sunLevel: this.sunLevel,
        hardLevel: this.hardLevel,
        sizeLevel: this.sizeLevel,
        isEdible: this.isEdible,
        isToxic: this.isToxic
      })
      .then((res) => {
        this.result = res.data
      })
      .catch((err) => {
        console.log(err)
      })
  },
  methods: {
    begin() {
      this.$router.push({
        name: 'PlantTestStartView'
      })
    },
    goPlantDetail(plantDictId) {
      this.$router.push({
        name: 'PlantDetail',
        params: { id: plantDictId }
      })
    }
  }
}
</script>
