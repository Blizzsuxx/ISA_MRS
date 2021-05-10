<template>
<el-main>
  <NavMeniZaPacijenta/>
<h2>Lista penala za tekuci mesec</h2>
<div style="margin-top: 20px">
  </div>
  <el-table
    ref="zaTabelu"
    :data="zaTabelu"
    style="width: 100%"
    @selection-change="handleSelectionChange">
    <el-table-column
      type="selection"
      width="55">
    </el-table-column>
    <el-table-column
      property="dan"
      label="Dan"
      width="120">
    </el-table-column>
    <el-table-column
      property="opis"
      label="opis"
      show-overflow-tooltip>
    </el-table-column>
    
  </el-table>
</el-main>
</template>

const { defineComponent, ref } = Vue;
<script>

   import { defineComponent, ref } from 'vue'
   import NavMeniZaPacijenta from "./NavMeniZaPacijenta.vue"
export default defineComponent ({
  setup() {
    return {
      input: ref('')
    }
  },
   data() {
      
      return {
       zaTabelu: this.$store.state.APenali.sviPenali,
      }
    },
    components : {NavMeniZaPacijenta},
    async mounted(){
      
      await this.$store.dispatch("APenali/dobaviPenale")
      this.zaTabelu =this.$store.state.APenali.sviPenali;
      
    },
    name: 'Penali',
    methods: {
     
    }
  })

</script>
