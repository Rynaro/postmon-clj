# postmon-clj
[![Clojars Project](https://img.shields.io/clojars/v/postmon.svg)](https://clojars.org/postmon)
[![Build Status](https://travis-ci.com/Rynaro/postmon-clj.svg?branch=master)](https://travis-ci.com/Rynaro/postmon-clj)

> Simple wrapper for PostmonAPI

## Installation

Include into your `project.clj`

```clj
[postmon "1.0.0"]
```

## Usage

### Getting CEP (zipcode)

```clj
(postmon/cep "02013010")
```

You'll get a map with CEP especification.

```clj
{:bairro "Santana", :cidade "São Paulo", :logradouro "Rua Henrique Bernardelli", :estado_info {:area_km2 "248.221,996", :codigo_ibge "35", :nome "São Paulo"}, :cep "02013010", :cidade_info {:area_km2 "1521,11", :codigo_ibge "3550308"}, :estado "SP"}
```

_Note: You don't need to format your CEP, you can use unformatted CEP string._

### Getting City Information

```clj
(postmon/city-data "SP" "Leme")
```

```clj
{:area_km2 "402,871", :codigo_ibge "3526704"}
```

### Getting UF (state) Information

```clj
(postmon/uf-data "SP")
```

```clj
{:area_km2 "248.221,996", :codigo_ibge "35", :nome "São Paulo"}
```

### Getting object package deliver tracking

```clj
(postmon/pack-deliver-tracker "ect" "mocked")
```

```clj
  {:codigo "mocked" :service "ect" :historico {:local "AG Liberdade - Sao Paulo/SP" :data "02/01/2020 13:23:03" :situacao "Postado"}}
```

_Note: Postmon only supports ECT (Correios - Empresa Brasileira de Correios e Telegrafos) yet!_

#### Exceptions

In case your query does return nothing from Postmon services, you will get a custom exception from slingshot! There is the exception for handling.

```clj
{:type :postmon.request/nothing-returned :message "nothing returned from postmon"}
```

## Contributing

Bug reports and pull requests are welcome on GitHub at https://github.com/Rynaro/postmon-clj.

## License

The lib is available as open source under the terms of the [MIT License](https://opensource.org/licenses/MIT).
