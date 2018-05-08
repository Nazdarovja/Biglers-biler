import React from 'react';
import { View, Text } from 'react-native';
import CheckBox from 'react-native-checkbox';
import CheckboxGroup from 'react-native-checkbox-group';

export default class Filter extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            categories: {
                Mini: false,
                Economy: false,
                Standard: false,
                Premium: false,
                Luxury: false
            },
            companies: {
                BiglersBigler: false,
                Gert: false,
                Elias: false,
                Devran: false
            }
        }
    }

    handleCategoryChange = async (selected) => {
        let categories = this.state.categories;
        for (var prop in categories) {
            selected.includes(prop) ? categories[prop] = true : categories[prop] = false;
        }
        await this.setState({ categories });

        const filterCompany = this.checkIfFilterShouldHappen(this.state.companies);
        this.props.filter(selected, filterCompany);
    }

    handleCompanyChange = async (selected) => {
        let companies = this.state.companies;
        for (var prop in companies) {
            selected.includes(prop) ? companies[prop] = true : companies[prop] = false;
        }
        await this.setState({ companies });

        const categoryFilters = this.checkIfFilterShouldHappen(this.state.categories);

        this.props.filter(categoryFilters, selected);
    }

    checkIfFilterShouldHappen = (data) => {
        let filters = [];
        for (let item in data) {
            if (data[item] === true)
                filters.push(item);
        }
        console.log(filters);
        return filters;
    }

    render() {
        return (
            <View>
                <Text>Categories</Text>
                <CheckboxGroup
                    callback={this.handleCategoryChange}
                    iconColor={"#00a2dd"}
                    iconSize={30}
                    checkedIcon="ios-checkbox-outline"
                    uncheckedIcon="ios-square-outline"
                    checkboxes={[
                        {
                            label: "Mini", // label for checkbox item
                            value: "Mini", // selected value for item, if selected, what value should be sent?
                            // if the item is selected by default or not.
                            selected: this.state.categories.Mini
                        },
                        {
                            label: "Economy",
                            value: "Economy",
                            selected: this.state.categories.Economy
                        },
                        {
                            label: "Standard",
                            value: "Standard",
                            selected: this.state.categories.Standard
                        },
                        {
                            label: "Premium",
                            value: "Premium",
                            selected: this.state.categories.Premium
                        },
                        {
                            label: "Luxury",
                            value: "Luxury",
                            selected: this.state.categories.Luxury
                        },
                    ]}
                    labelStyle={{
                        color: '#333'
                    }}
                    rowStyle={{
                        flexDirection: 'row'
                    }}
                    rowDirection={"column"}
                />

                <Text>Companies</Text>
                <CheckboxGroup
                    callback={this.handleCompanyChange}
                    iconColor={"#00a2dd"}
                    iconSize={30}
                    checkedIcon="ios-checkbox-outline"
                    uncheckedIcon="ios-square-outline"
                    checkboxes={[
                        {
                            label: "BiglersBiler", // label for checkbox item
                            value: "BiglersBiler", // selected value for item, if selected, what value should be sent?
                            // if the item is selected by default or not.
                            selected: this.state.companies.BiglersBigler
                        },
                        {
                            label: "Gert",
                            value: "Gert",
                            selected: this.state.companies.Gert
                        },
                        {
                            label: "Elias",
                            value: "Elias",
                            selected: this.state.companies.Elias
                        },
                        {
                            label: "Devran",
                            value: "Devran",
                            selected: this.state.companies.Devran
                        }
                    ]}
                    labelStyle={{
                        color: '#333'
                    }}
                    rowStyle={{
                        flexDirection: 'row'
                    }}
                    rowDirection={"column"}
                />

                {/*<CheckBox label="Mini" style={{width: 20, height: 20}} value={this.state.categories.Mini} onChange={(checked) => console.log('I am checked', checked)}/>
                    {/*<input name="Economy" type="checkbox" checked={this.state.categories.Economy} onClick={this.handleCategoryChange} />
                    <input name="Standard" type="checkbox" checked={this.state.categories.Standard} onClick={this.handleCategoryChange} />
                    <input name="Premium" type="checkbox" checked={this.state.categories.Premium} onClick={this.handleCategoryChange} />
                    <input name="Luxury" type="checkbox" checked={this.state.categories.Luxury} onClick={this.handleCategoryChange} />

               Companies
                    <input name="BiglersBigler" type="checkbox" checked={this.state.companies.BiglersBigler} onClick={this.handleCompanyChange} />
                    <input name="Gert" type="checkbox" checked={this.state.companies.Gert} onClick={this.handleCompanyChange} />
                    <input name="Elias" type="checkbox" checked={this.state.companies.Elias} onClick={this.handleCompanyChange} />
        <input name="Devran" type="checkbox" checked={this.state.companies.Devran} onClick={this.handleCompanyChange} />*/}

            </View>
        );
    }
}