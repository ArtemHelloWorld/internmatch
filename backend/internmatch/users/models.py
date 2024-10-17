import django.contrib.auth.models
import django.db.models
from django.core.validators import MaxValueValidator, MinValueValidator


class User(django.contrib.auth.models.AbstractUser):
    email = django.db.models.EmailField(
        null=True,
        blank=True,
        verbose_name='почта'
    )
    profile_image = django.db.models.ImageField(
        upload_to='profile_images/%Y/%m/%d',
        null=True,
        blank=True,
        verbose_name='фотография',
    )

    def __str__(self):
        return f'Пользователь {self.pk}'

    class Meta:
        verbose_name = 'Пользователь'
        verbose_name_plural = 'Пользователи'

class Intern(django.db.models.Model):
    user = django.db.models.OneToOneField(
        User,
        on_delete=django.db.models.CASCADE,
        verbose_name='intern'
    )

    def __str__(self):
        return f'Стажер {self.pk}'

    class Meta:
        verbose_name = 'Стажер'
        verbose_name_plural = 'Стажеры'


class Employer(django.db.models.Model):
    user = django.db.models.OneToOneField(
        User,
        on_delete=django.db.models.CASCADE,
        verbose_name='employer'
    )

    def __str__(self):
        return f'Работодатель {self.pk}'

    class Meta:
        verbose_name = 'Работодатель'
        verbose_name_plural = 'Работодалели'


class Skill(django.db.models.Model):
    intern = django.db.models.ForeignKey(
        Intern,
        on_delete=django.db.models.CASCADE,
        related_name='skills'
    )
    name = django.db.models.CharField(
        max_length=50,
        verbose_name='название'
    )
    rate = django.db.models.PositiveSmallIntegerField(
        validators=[
            MaxValueValidator(10),
            MinValueValidator(1)
        ]
    )

    def __str__(self):
        return f'{self.name} {self.rate}/10'

    class Meta:
        verbose_name = 'Скилл'
        verbose_name_plural = 'Скиллы'