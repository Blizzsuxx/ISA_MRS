<template>
  <el-table
    ref="referenca"
    highlight-current-row
    @current-change="handleCurrentChange"
    
    :data="godisnjiOdmori"
    style="width: 100%"
   >
   
    <el-table-column
      property="korisnicko"
      label="Korisnicko ime"
     >
    </el-table-column>
    <el-table-column
      property="datumOd"
      label="Datum od"
      >
    </el-table-column>
    <el-table-column
      property="datumDo"
      label="Datum do"
      >
    </el-table-column>
    <el-table-column
      property="email"
      label="Email"
      :formatter="formirajOdobren"
      >
    </el-table-column>
  </el-table>
  <div style="margin-top: 20px">
   
    
  </div>
</template>

<script>


export default {
  name: 'GodisnjiOdmoriTabela',
  data() {
      return {
        search : ''
      }
    },
  components:{
  
  },
  props: ['godisnjiOdmori','referenca'],
  methods: {
      formirajOdobren(row){
        if(row.odobre){
          return "Odobren"
        }
        return "Nije odobren"
      },
      handleCurrentChange(val) {
        this.$refs.referenca.setCurrentRow(val)
        this.$emit("promjenjena-selekcija",val)
      },
      
    },
    emits: ['promjenjena-selekcija']
  }
  

</script>
