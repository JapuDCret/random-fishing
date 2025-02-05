# Random Fishing Mod

Inspired by a [video](https://www.youtube.com/watch?v=PU7xUs-XhLI) by Loopy

## Installing

Download the jar from the [releases](https://github.com/JapuDCret/random-fishing/releases) and put it inside your mods folder.

Requires fabric, fabric-api and [**fabric-language-kotlin**](https://modrinth.com/mod/fabric-language-kotlin).

## Building & deploying

```shell
./gradlew remapJar && cp build/libs/random-fishing-1.0.0+1.21.4.jar ~/.local/share/multimc/instances/1.21.4_modded/.minecraft/mods
```

### Trying out

Give yourself a fishing rod in game

```shell
/give @p minecraft:fishing_rod[minecraft:enchantments={levels:{"minecraft:lure":5}}]
```