# Пошаговая симуляция 2D-мира с травоядными и хищниками
![Симуляция](https://i.imgur.com/Kxut08b.png)
## Описание проекта

Данный проект представляет собой пошаговую симуляцию 2D-мира, населенного травоядными и хищниками. В мире также
присутствуют ресурсы (трава), которыми питаются травоядные, и статичные объекты, не взаимодействующие с существами, а
лишь заполняющие пространство.
Техническое задание проекта:https://zhukovsd.github.io/java-backend-learning-course/Projects/Simulation/

## Особенности проекта

- Поддерживаемые действия существ:
    - Травоядные могут двигаться к траве или съесть ее.
    - Хищники могут двигаться к травоядным или съесть их.
- Алгоритм поиска пути:
    - В проекте используется алгоритм поиска в ширину для нахождения кратчайшего пути от объекта к его цели.
    - При поиске пути учитываются стационарные объекты, такие как деревья и камни, и они исключаются из рассмотрения.
- Рендерер:
    - Реализован рендерер, который отображает текущее состояние симуляции в консольном выводе.

## Запуск проекта

На старте формируется "игровое" поле, где площадь занимают камни, деревья, трава, хищники и травоядные. Сущности
распределяются в пределах заданного объема поля. Цель симуляции: хищники должны съесть всех травоядных, а травоядные
должны съесть всю траву. Симуляция игрового мира завершается, когда не остается либо травы, либо травоядных. В процессе
симуляции можно поставить ее на паузу, а затем продолжить с места остановки.

## Управление

- Для постановки симуляции на паузу нажмите клавишу "p".
- Для продолжения симуляции после паузы нажмите клавишу "r".
- Чтобы выйти из симуляции, нажмите клавишу "q".

## Здоровье существ

Каждый ход существа снижается их здоровье. Здоровье восстанавливается, когда существо съедает пищу.
