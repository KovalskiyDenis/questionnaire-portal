<template>
    <div>
        <navbar full-navbar="true"></navbar>
    </div>
</template>

<script>
    import Navbar from "../components/navbar/Navbar.vue"

    export default {
        components: {
            Navbar
        },
        name: "ResponsesPage",
        data() {
            return {
                fields: [],
                responses: []
            }
        },
        created() {
            this.$resource('/responsesPage').get().then(result => {
                if(result.ok) {
                    console.log(result.data)
                }
            })
        },
        methods: {

        }
    }
</script>

<style scoped>

</style>