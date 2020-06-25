<template>
    <div class="container">
        <navbar full-navbar="true"></navbar>
        <div class="d-flex justify-content-center">
            <div class="card w-75">
                <div class="card-body">
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">
                            Responses
                        </li>
                        <li class="list-group-item">
                            <table class="table table-striped table-sm table-borderless">
                                <thead>
                                <tr>
                                    <th v-for="field in fields" scope="col">{{field.label}}</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr v-for="response in responses">
                                    <td v-for="field in fields">
                                        <div v-for="fieldResponse in response.response">
                                            <div v-if="field.id === fieldResponse.fieldId">
                                                <div v-if="fieldResponse.fieldResponse">
                                                    {{fieldResponse.fieldResponse}}
                                                </div>
                                                <div v-else>
                                                    N/A
                                                </div>
                                            </div>
                                        </div>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
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
                    this.fields = result.data.fields
                    this.responses = result.data.responses
                    console.log(this.responses[0].response)
                    console.log(this.responses[0].fieldId)
                    console.log(this.responses[0].fieldResponse)
                }
            })
        },
        methods: {

        }
    }
</script>

<style scoped>

</style>