import django.db.models

import users.models


class Vacancy(django.db.models.Model):
    employer = django.db.models.ForeignKey(
        users.models.Employer,
        on_delete=django.db.models.CASCADE,
        related_name='vacancies'
    )
    title = django.db.models.CharField(
        max_length=50,
        default='Заголовок'
    )
    description = django.db.models.CharField(
        max_length=1000,
        default='описание'
    )
    salary = django.db.models.IntegerField(
        null=True,
        blank=True,
        verbose_name='зарпалата'
    )
    duration = django.db.models.IntegerField(
        null=True,
        blank=True,
        verbose_name='длительность в месяцах'
    )
    image = django.db.models.ImageField(
        upload_to='profile_images/%Y/%m/%d',
        null=True,
        blank=True,
        verbose_name='фотография',
    )

    def __str__(self):
        return f'Вакансия {self.pk}'

    class Meta:
        verbose_name = 'Вакансия'
        verbose_name_plural = 'Вакансии'
    

class EchoVacancy(django.db.models.Model):
    text = django.db.models.CharField(
        max_length=50,
        default='сопроводительный текст'
    )
    intern = django.db.models.ForeignKey(
        users.models.Intern,
        on_delete=django.db.models.CASCADE,
        related_name='echo_vacancies'
    )
    vacancy = django.db.models.ForeignKey(
        Vacancy,
        on_delete=django.db.models.CASCADE,
        related_name='echo_vacancies'
    )
    time_created = django.db.models.DateTimeField(
        auto_now_add=True
    )
    time_updated = django.db.models.DateTimeField(
        auto_now=True
    )

    def __str__(self):
        return f'Отклик {self.pk}'

    class Meta:
        verbose_name = 'Отклик'
        verbose_name_plural = 'Отклики'
