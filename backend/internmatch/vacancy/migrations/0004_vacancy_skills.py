# Generated by Django 3.2.16 on 2024-10-26 17:06

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('vacancy', '0003_auto_20241025_1251'),
    ]

    operations = [
        migrations.AddField(
            model_name='vacancy',
            name='skills',
            field=models.CharField(default='', max_length=100, verbose_name='необходимые навыки'),
            preserve_default=False,
        ),
    ]
