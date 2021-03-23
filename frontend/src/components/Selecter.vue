<template>
  <el-select v-model="value" filterable placeholder="Select">
    <el-option
      v-for="item in options.split(';')"
      :key="item"
      :label="item"
      :value="item">
    </el-option>
  </el-select>
</template>

<script>
  export default {
      name: 'Selecter',
      props: {options : String},
    data() {
      return {
        value : null

      }
    }
    
  }
</script>