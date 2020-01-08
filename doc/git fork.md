# git fork 

- git fork 他人项目后如何拉取合并到自己的项目中
- 命令1: `git remote add 分支名称 fork项目的git地址`
```shell
git remote add other_master https://github.com/doocs/source-code-hunter.git
```

- 命令2: `git remote -v `
```shell script
git remote -v 
origin  https://github.com/huifer/source-code-hunter.git (fetch)
origin  https://github.com/huifer/source-code-hunter.git (push)
other_master    https://github.com/doocs/source-code-hunter.git (fetch)
other_master    https://github.com/doocs/source-code-hunter.git (push)

```
- 命令3: `git fetch 分支名称`
```shell script
git fetch other_master
```
```text
remote: Enumerating objects: 92, done.
remote: Counting objects: 100% (84/84), done.
remote: Compressing objects: 100% (52/52), done.
remote: Total 72 (delta 37), reused 52 (delta 19), pack-reused 0
Unpacking objects: 100% (72/72), done.
From https://github.com/doocs/source-code-hunter
 * [new branch]      imgbot     -> other_master/imgbot
 * [new branch]      master     -> other_master/master
```
- 命令4: 合并
```shell script
git merge other_master/master
```
